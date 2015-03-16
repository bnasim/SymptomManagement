package org.magnum.mobilecloud.integration.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

import org.junit.Test;
import org.magnum.mobilecloud.video.TestData;
import org.magnum.mobilecloud.video.client.SecuredRestBuilder;
import org.magnum.mobilecloud.video.client.SecuredRestException;
import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;


import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.ApacheClient;

import com.google.gson.JsonObject;

/**
 * 
 * This integration test sends a POST request to the VideoServlet to add a new
 * video and then sends a second GET request to check that the video showed up
 * in the list of videos. Actual network communication using HTTP is performed
 * with this test.
 * 
 * The test requires that the VideoSvc be running first (see the directions in
 * the README.md file for how to launch the Application).
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a VideoSvc object are essentially identical.
 * All that changes is the setup of the videoService variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class VideoSvcClientApiTest {

	private final String USERNAME = "admin";
	private final String PASSWORD = "pass";
	private final String CLIENT_ID = "mobile";
	private final String READ_ONLY_CLIENT_ID = "mobileReader";

	private final String TEST_URL = "https://localhost:8443";

	private VideoSvcApi videoService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + VideoSvcApi.TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(CLIENT_ID)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(VideoSvcApi.class);

	private VideoSvcApi readOnlyVideoService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + VideoSvcApi.TOKEN_PATH)
			.setUsername(USERNAME)
			.setPassword(PASSWORD)
			.setClientId(READ_ONLY_CLIENT_ID)
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(VideoSvcApi.class);

	private VideoSvcApi invalidClientVideoService = new SecuredRestBuilder()
			.setLoginEndpoint(TEST_URL + VideoSvcApi.TOKEN_PATH)
			.setUsername(UUID.randomUUID().toString())
			.setPassword(UUID.randomUUID().toString())
			.setClientId(UUID.randomUUID().toString())
			.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(VideoSvcApi.class);

	private Video video = TestData.randomVideo();
	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVideoAddAndListWithOneToMany() throws Exception {
		
		// If we try to add a Video to a Category that doesn't
		// exist yet, it should fail.
		
		try{
			// Add the video
			videoService.addVideo(video);
			fail("It shouldn't be possible to add a Video to a Category that doens't exist");
		}catch(Exception e){
			// Good, the Video service prevented us from adding a Video to a Category
			// that doesn't exist
		}
		
		// Now, let's add the Video's Category to the server 
		boolean addedCategory = videoService.addCategory(video.getCategory());
		assertTrue(addedCategory);
		
		// Once the target Video category exists, we can add the Video
		boolean addedVideo = videoService.addVideo(video);
		assertTrue(addedVideo);

		// We should get back the video that we added above
		Collection<Video> videos = videoService.getVideoList();
		assertTrue(videos.contains(video));
		
		// The Video should show up in the appropriate Category
		Collection<Video> videosByCategory = videoService.getVideoListForCategory(video.getCategory().getName());
		assertTrue(videosByCategory.contains(video));
	}

	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	
	
}
