package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import br.com.thiengo.superplacar.domain.Goal;


public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {
    private Context context;
    private int idLayout;
    private List<Goal> goals;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime;
        TextView tvNome;

        ViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
        }

        private void setData( Goal goal ){
            tvTime.setText( goal.getTime() );
            tvNome.setText( goal.getNome() );
        }
    }

    public GoalsAdapter(Context context, int idLayout){
        this.context = context;
        this.idLayout = idLayout;
        this.goals = new ArrayList<>();
    }

    @Override
    public GoalsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate( idLayout, parent, false );

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( goals.get( position ) );
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public void setGoasl( List<Goal> goals ){
        this.goals.clear();
        this.goals.addAll( goals );
        notifyDataSetChanged();
    }
}
