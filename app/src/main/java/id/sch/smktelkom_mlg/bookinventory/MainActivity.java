package id.sch.smktelkom_mlg.bookinventory;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.bookinventory.adapter.BooksAdapter;
import id.sch.smktelkom_mlg.bookinventory.adapter.DividerDecoration;
import id.sch.smktelkom_mlg.bookinventory.helper.HelperFunction;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerBook) RecyclerView recyclerBook;
    @BindView(R.id.fab) FloatingActionButton btnAdd;
    private List<Book> bookList = new ArrayList<>();
    private BooksAdapter mAdapter;

    public int TO_FORM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Books Catalog");

        mAdapter = new BooksAdapter(this, bookList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerBook.setLayoutManager(mLayoutManager);
        recyclerBook.setItemAnimator(new DefaultItemAnimator());
        recyclerBook.addItemDecoration(new DividerDecoration(this));

        recyclerBook.setAdapter(mAdapter);
        recyclerBook.addOnItemTouchListener(new HelperFunction.RecyclerTouchListener(this, recyclerBook, new HelperFunction.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareBookData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void prepareBookData() {
        Book book = new Book("9780439064873", "Harry Potter And The Chamber Of Secrets", "J.K. Rowling", 2000, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.chamber);
        bookList.add(book);

        book = new Book("9780316015844", "Twilight (The Twilight Saga, Book 1)", "Stephanie Meyer", 2006, "Fantasy, Drama, Romance", "This is some synopsis", R.drawable.twilight);
        bookList.add(book);

        book = new Book("9781484724989", "Journey to Star Wars: The Force Awakens Lost Stars", "Claudia Gray", 2015, "Action, Thriller, Science Fiction", "This is some synopsis",R.drawable.starwars);
        bookList.add(book);
        
        book = new Book("9780439136365", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2001, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.azkaban);
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data){
        super.onActivityResult(requestCode,resultCode, Data);
        if(resultCode == RESULT_OK && requestCode == TO_FORM){
            Book bookForm = (Book) Data.getExtras().getSerializable("book");
            bookList.add(bookForm);
            Toast.makeText(this, "Book" + bookForm.getBook_title() + " successfully added", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
        }
    }
}
