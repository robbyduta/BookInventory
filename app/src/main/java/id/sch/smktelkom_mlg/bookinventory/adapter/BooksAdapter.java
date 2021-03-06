package id.sch.smktelkom_mlg.bookinventory.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.bookinventory.Book;
import id.sch.smktelkom_mlg.bookinventory.R;

/**
 * Created by Robby Duta I M on 17/04/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder>{
    private List<Book> books;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtBookTitle) TextView txtBookTitle;
        @BindView(R.id.txtOtherInfo) TextView txtOtherInfo;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public BooksAdapter(Context context, List<Book> bookList) {
        this.books = bookList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = books.get(position);
        holder.txtBookTitle.setText(book.getBook_title());
        holder.txtOtherInfo.setText(book.getBook_author());
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }
}
