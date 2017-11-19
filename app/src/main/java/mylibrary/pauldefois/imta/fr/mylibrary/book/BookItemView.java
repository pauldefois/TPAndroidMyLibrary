package mylibrary.pauldefois.imta.fr.mylibrary.book;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mylibrary.pauldefois.imta.fr.mylibrary.R;

/**
 * Layout custom pour l'affichage des books sous forme de liste
 *
 * @author Paul Defois
 */
public class BookItemView extends LinearLayout {
    private TextView nameTextView;
    private ImageView coverImageView;

    public BookItemView(Context context) {
        super(context);
    }

    public BookItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BookItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        nameTextView = findViewById(R.id.nameTextView);
        coverImageView = findViewById(R.id.coverImageView);
    }

    public void bindView(final Book book, final OnClickBookItemListener listener) {
        nameTextView.setText(book.getTitle());
        Glide.with(this)
                .load(book.getCover())
                .into(coverImageView);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickBook(book);
            }
        });
    }
}
