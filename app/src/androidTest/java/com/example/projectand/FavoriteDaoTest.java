package com.example.projectand;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projectand.Dao.FavoriteDao;
import com.example.projectand.POJO.Favorite;
import com.example.projectand.databases.FavoriteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import 	androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class FavoriteDaoTest implements LifecycleOwner {
    private FavoriteDatabase database;
    private FavoriteDao dao;
    private List<Favorite> list;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @Mock
    private Observer<List<Favorite>> observer;

    @Before
    public void initDb() throws Exception {

        MockitoAnnotations.initMocks(this);
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase.class)
                .allowMainThreadQueries().build();
        dao = database.favoriteDao();
    }


    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetUser() {
        String mockUserId = "TESTID";
        String mockRecipeKey = "TESTKEY";
        Favorite FAV = new Favorite(mockUserId, mockRecipeKey, 1);
        list.add(FAV);
        dao.getAllFavorites().observeForever(observer);
        database.favoriteDao().insert(FAV);

        verify(observer).onChanged(Collections.singletonList(FAV));

        assertThat(list.size(), is(1));
        Favorite dbFav = list.get(0);
        assertEquals(dbFav.getIdFav(), FAV.getIdFav());
        assertEquals(dbFav.getIdUser(), FAV.getIdUser());
        assertEquals(dbFav.getIdRecipe(), FAV.getIdRecipe());
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
