package com.gamecodeschool.pathbuffs.jsjf.ListViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gamecodeschool.pathbuffs.R;
import com.gamecodeschool.pathbuffs.TabFragments.TabFragmentFeatsAbilities;
import com.gamecodeschool.pathbuffs.jsjf.BonusManagement.Feats;
import com.gamecodeschool.pathbuffs.jsjf.ClickListener;
import com.gamecodeschool.pathbuffs.jsjf.Profiles.AllProfiles;

import java.lang.ref.WeakReference;
import java.util.List;

public class ProfileFeatsViewAdaptor extends RecyclerView.Adapter<ProfileFeatsViewAdaptor.MyViewHolder> {
    private List<Feats> mDataList;
    private final ClickListener listener;

    public ProfileFeatsViewAdaptor(List<Feats> mFeatsList, ClickListener listner) {
        this.mDataList = mFeatsList;
        this.listener = listner;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView featName;
        public Button buttonView;
        private WeakReference<ClickListener> listenerRef;


        public MyViewHolder(final View view, ClickListener listener){
            super(view);
            listenerRef = new WeakReference<>(listener);


            featName = (TextView) view.findViewById(R.id.profile_feat_name);
            buttonView = (Button) view.findViewById(R.id.profile_delete_feat);

            buttonView.setOnClickListener(this);
        }

        public void onClick(View v)
        {
            if(v.getId() == buttonView.getId())
            {
                AllProfiles.currentProfile.removeFeatFromProfile((String) featName.getText());
                TabFragmentFeatsAbilities.updatelist();
            }
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
        public boolean onLongClick(View v)
        {
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_profile_feats, parent, false);

        return new MyViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Feats feat = mDataList.get(position);
        holder.featName.setText(feat.getName());


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}