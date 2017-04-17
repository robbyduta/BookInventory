package id.sch.smktelkom_mlg.bookinventory.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.bookinventory.Book;
import id.sch.smktelkom_mlg.bookinventory.R;

import static android.R.attr.bitmap;

public class BookFormActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView (R.id.editTitle)
    EditText editTitle;
    @BindView (R.id.editAuthor) EditText editBookAuthor;
    @BindView (R.id.editTitle) EditText editBookTitle;
    @BindView (R.id.editGenre) EditText editGenre;
    @BindView (R.id.editIsbn) EditText editISBN;
    @BindView (R.id.editPublished) EditText editPublishYear;
    @BindView (R.id.editSynopsis) EditText editSynopsis;
    @BindView(R.id.btnsave)
    Button btnSave;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            book = (Book) bundle.getSerializable("bookEdit");
            editISBN.setText(book.getISBN());
            editPublishYear.setText(book.getPublished_year() + "");
            editBookAuthor.setText(book.getBook_author());
            editBookTitle.setText(book.getBook_title());
            editGenre.setText(book.getBook_genre());
            editSynopsis.setText(book.getBook_synopsis());
            btnSave.setEnabled(false);
            getSupportActionBar().setTitle(book.getBook_title());
        } else {
            book = new Book();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    book.setISBN(editISBN.getText().toString());
                    book.setBook_title(editBookTitle.getText().toString());
                    book.setBook_author(editBookAuthor.getText().toString());
                    book.setPublished_year(Integer.parseInt(editPublishYear.getText().toString()));
                    book.setBook_genre(editGenre.getText().toString());
                    book.setBook_synopsis(editSynopsis.getText().toString().equals("")? "-" : editSynopsis.getText().toString());

                    Intent i = new Intent();
                    i.putExtra("book",book);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }

    private boolean validate(){
        boolean valid = true;

        String isbn = editISBN.getText().toString();
        String booktitle = editTitle.getText().toString();
        String bookauthor = editBookAuthor.getText().toString();
        String publishedyear = editPublishYear.getText().toString();

        if (isbn.isEmpty()){
            editISBN.setError("Enter ISBN");
            valid = false;
        } else {
            editISBN.setError(null);
        }

        if (booktitle.isEmpty()){
            editTitle.setError("Enter Book Title");
            valid = false;
        } else {
            editTitle.setError(null);
        }

        if (bookauthor.isEmpty()){
            editBookAuthor.setError("Enter Author");
            valid = false;
        } else {
            editBookAuthor.setError(null);
        }

        if (publishedyear.isEmpty()|| publishedyear.length()<4){
            editPublishYear.setError("Publish Year empty or must in yyyy format");
            valid = false;
        } else {
            editPublishYear.setError(null);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return valid;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
