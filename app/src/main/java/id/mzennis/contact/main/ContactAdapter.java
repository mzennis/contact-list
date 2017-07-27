package id.mzennis.contact.main;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import id.mzennis.contact.R;
import id.mzennis.contact.base.BaseListAdapter;
import id.mzennis.contact.base.BaseViewHolder;
import id.mzennis.contact.model.Contact;
import id.mzennis.contact.util.MathUtil;
import id.mzennis.contact.util.StringUtil;

/**
 * Created by mzennis on 7/26/17.
 */

public class ContactAdapter extends BaseListAdapter<Contact, ContactAdapter.ViewHolder> {

    public ContactAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.row_contact;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, viewType), onItemClickListener);
    }

    private int[] colors = {R.color.pink, R.color.purple, R.color.orange, R.color.blue, R.color.darkblue,
            R.color.cyan, R.color.darkcyan, R.color.green, R.color.darkgreen, R.color.lightgreen, R.color.lightesgreen,
            R.color.gray, R.color.brown, R.color.jingga};

    public class ViewHolder extends BaseViewHolder<Contact> {

        private String firstNameLastest = "";

        @BindView(R.id.iv_favorite) ImageView ivFavorite;
        @BindView(R.id.tv_alphabet) TextView tvAlphabet;
        @BindView(R.id.tv_initial) TextView tvInitial;
        @BindView(R.id.tv_name) TextView tvName;

        public ViewHolder(View itemView, BaseListAdapter.OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }

        @Override
        public void bind(Contact item) {

            // alphabet
            String alphabet = item.getFirstName().substring(0, 1).toLowerCase();
            boolean isVisibleAlphabet = !alphabet.toLowerCase().equals(firstNameLastest);
            if (isVisibleAlphabet) {
                tvAlphabet.setVisibility(View.VISIBLE);
                if (StringUtil.isAlphabet(alphabet))
                    tvAlphabet.setText(alphabet.toUpperCase());
                else tvAlphabet.setText("#");
            } else tvAlphabet.setVisibility(View.GONE);

            // favorite
            boolean isVisibleFavorite = (getAdapterPosition() == 0 && item.isFavorite());
            if (isVisibleFavorite) {
                ivFavorite.setVisibility(View.VISIBLE);
            } else {
                ivFavorite.setVisibility(View.GONE);
            }

            if (item.isFavorite())
                tvAlphabet.setVisibility(View.GONE);

            if (!isVisibleAlphabet && !isVisibleFavorite)
                ivFavorite.setVisibility(View.INVISIBLE);

            tvInitial.setBackgroundResource(R.drawable.rounded_bg);

            GradientDrawable bgAlphabet = (GradientDrawable) tvInitial.getBackground();
            bgAlphabet.setColor(ContextCompat.getColor(context, colors[MathUtil.random(0, 13)]));

            tvInitial.setText(alphabet.toUpperCase());
            tvName.setText(item.getFirstName() + " " + item.getLastName());

            if (StringUtil.isAlphabet(alphabet))
                this.firstNameLastest = alphabet;
            else
                this.firstNameLastest = "1";
        }
    }
}
