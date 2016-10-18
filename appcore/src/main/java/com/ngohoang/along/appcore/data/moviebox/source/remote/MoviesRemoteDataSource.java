package com.ngohoang.along.appcore.data.moviebox.source.remote;



import com.ngohoang.along.appcore.data.moviebox.backend.MovieBoxService;
import com.ngohoang.along.appcore.data.moviebox.model.Movie;
import com.ngohoang.along.appcore.data.moviebox.source.MoviesDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Long on 10/6/2016.
 */
@Singleton
public class MoviesRemoteDataSource implements MoviesDataSource {
    private static MoviesRemoteDataSource INSTANCE;
    private static final int SERVICE_LATENCY_IN_MILLIS = 1000;
    MovieBoxService movieBoxService;
    // Prevent direct instantiation.
    @Inject
    public MoviesRemoteDataSource(MovieBoxService movieBoxService) {
        this.movieBoxService= movieBoxService;
    }

    @Override
    public Observable<List<Movie>> getMovieList(int page) {
        return movieBoxService.getMovies(page);
    }
    @Override
    public void saveCompetition(Movie competition) {

    }
}
