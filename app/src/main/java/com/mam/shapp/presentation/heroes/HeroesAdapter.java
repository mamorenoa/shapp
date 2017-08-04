package com.mam.shapp.presentation.heroes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mam.shapp.R;
import com.mam.shapp.domain.model.Hero;
import com.mam.shapp.utils.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolderHeroe> {

    public interface OnItemClickListener {
        void onItemClick(Hero event, ImageView image);
    }

    private OnItemClickListener clickListener;
    private List<Hero> heroes;
    private ImageLoader imageLoader;

    public HeroesAdapter(List<Hero> heroes, ImageLoader imageLoader) {
        this.heroes = heroes;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolderHeroe onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroe, parent, false);
        ViewHolderHeroe heroeViewHolder = new ViewHolderHeroe(view);
        return heroeViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderHeroe holder, int position) {
        final Hero heroe = heroes.get(position);
        holder.textName.setText(heroe.getName());
        holder.textRealName.setText(heroe.getRealName());
        imageLoader.loadImage(heroe.getPhoto(), holder.imagePhoto, R.drawable.marvelplaceholder, true);
        holder.cardHeroe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(heroe, holder.imagePhoto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ViewHolderHeroe extends RecyclerView.ViewHolder {
        @Bind(R.id.cardHeroe)
        protected CardView cardHeroe;
        @Bind(R.id.imageHero)
        protected ImageView imagePhoto;
        @Bind(R.id.textNameValue)
        protected TextView textName;
        @Bind(R.id.textRealNameValue)
        protected TextView textRealName;

        ViewHolderHeroe(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
