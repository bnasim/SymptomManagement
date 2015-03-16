package org.magnum.videoup.client;

import java.util.Collection;


import org.magnum.videoup.client.Category;
import org.magnum.videoup.client.Video;


import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * This interface defines an API for a VideoSvc that uses @OneToMany. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * 
 * 
 * @author jules
 *
 */
public interface VideoSvcApi {
	
	public static final String TITLE_PARAMETER = "title";
	public static final String TOKEN_PATH = "/oauth/token";
	// The path where we expect the VideoSvc to live
	public static final String VIDEO_SVC_PATH = "/video";
	
	// The path were we expect Categories to live
	public static final String CATEGORY_SVC_PATH = "/category";

	// The path to search videos by title
	public static final String VIDEO_TITLE_SEARCH_PATH = VIDEO_SVC_PATH + "/find";

	@GET(VIDEO_SVC_PATH)
	public Collection<Video> getVideoList();
	
	@POST(VIDEO_SVC_PATH)
	public boolean addVideo(@Body Video v);
	
	@GET(VIDEO_TITLE_SEARCH_PATH)
	public Collection<Video> findByTitle(@Query(TITLE_PARAMETER) String title);
	
	@GET(VIDEO_SVC_PATH+"/{category}")
	public Collection<Video> getVideoListForCategory(@Path("category") String categoryName);
	
	@POST(CATEGORY_SVC_PATH)
	public boolean addCategory(@Body Category c);
	
}
