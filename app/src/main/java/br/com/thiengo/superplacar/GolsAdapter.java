package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import br.com.thiengo.superplacar.domain.Gol;


public class GolsAdapter extends RecyclerView.Adapter<GolsAdapter.ViewHolder> {
    private Context context;
    private int idLayout;
    private List<Gol> gols;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime;
        TextView tvNome;

        ViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
        }

        private void setData( Gol gol){
            tvTime.setText( gol.getTime() );
            tvNome.setText( gol.getNome() );
        }
    }

    public GolsAdapter(Context context, int idLayout){
        this.context = context;
        this.idLayout = idLayout;
        this.gols = new ArrayList<>();
    }

    @Override
    public GolsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate( idLayout, parent, false );

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( gols.get( position ) );
    }

    @Override
    public int getItemCount() {
        return gols.size();
    }

    public void setGoasl( List<Gol> gols){
        this.gols.clear();
        this.gols.addAll(gols);
        notifyDataSetChanged();
    }
}
