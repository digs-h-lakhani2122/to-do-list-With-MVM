package coatocl.exaatocl.roomdatabasefinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private Context context;
    private List<CustomModel>listTask;

    public Adapter(Context context, List<CustomModel> listTask) {
        this.context = context;
        this.listTask = listTask;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.show,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomModel customModel = listTask.get(position);

        holder.title.setText(customModel.getTask_title());
        holder.date.setText(customModel.getTask_date());
        holder.description.setText(customModel.getTask_description());

    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,description,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView3);
            date = itemView.findViewById(R.id.textView4);
            description = itemView.findViewById(R.id.textView5);
        }
    }
}
