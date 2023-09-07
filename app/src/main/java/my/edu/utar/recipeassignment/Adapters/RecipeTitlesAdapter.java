package my.edu.utar.recipeassignment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecipeTitlesAdapter extends RecyclerView.Adapter<RecipeTitlesAdapter.ViewHolder> {

    private List<String> recipeTitles;

    public RecipeTitlesAdapter(List<String> recipeTitles) {
        this.recipeTitles = recipeTitles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = recipeTitles.get(position);
        holder.recipeTitleTextView.setText(title);
    }

    @Override
    public int getItemCount() {
        return recipeTitles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitleTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeTitleTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
