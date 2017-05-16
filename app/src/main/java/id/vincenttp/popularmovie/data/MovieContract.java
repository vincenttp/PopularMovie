package id.vincenttp.popularmovie.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DELL on 5/16/2017.
 */

public class MovieContract {
    public static final String AUTHORITY = "id.vincenttp.popularmovie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_TASKS = "movie";

    public static final class MovieEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VOTE = "vote";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_SINOPSIS = "sinopsis";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_IMAGE = "image";
    }
}
