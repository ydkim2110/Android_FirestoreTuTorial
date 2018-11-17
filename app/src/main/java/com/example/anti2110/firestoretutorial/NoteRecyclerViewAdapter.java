package com.example.anti2110.firestoretutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anti2110.firestoretutorial.Models.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anti2110 on 2018-11-17
 */
public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "NoteRecyclerViewAdapter";

    private List<Note> mNotes = new ArrayList<>();
    private IMainActivity mIMainActivity;
    private Context mContext;
    private int mSelectedNoteIndex;

    public NoteRecyclerViewAdapter(Context context, List<Note> notes) {
        mContext = context;
        mNotes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).title.setText(mNotes.get(position).getTitle());

            SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy");
            String date = spf.format(mNotes.get(position).getTimestamp());
            ((ViewHolder)holder).timestamp.setText(date);
        }

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void updateNote(Note note) {
        mNotes.get(mSelectedNoteIndex).setTitle(note.getTitle());
        mNotes.get(mSelectedNoteIndex).setContent(note.getContent());
        notifyDataSetChanged();
    }
    
    public void removeNote(Note note) {
        mNotes.remove(note);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mIMainActivity = (IMainActivity) mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, timestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            timestamp = itemView.findViewById(R.id.timestamp);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mSelectedNoteIndex = getAdapterPosition();
            mIMainActivity.onNoteSelected(mNotes.get(mSelectedNoteIndex));
        }
    }

}
