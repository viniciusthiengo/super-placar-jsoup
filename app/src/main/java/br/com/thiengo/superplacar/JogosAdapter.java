package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import br.com.thiengo.superplacar.domain.Goal;
import br.com.thiengo.superplacar.domain.Jogo;


public class JogosAdapter extends RecyclerView.Adapter<JogosAdapter.ViewHolder> {
    private Context context;
    private List<Jogo> jogos;

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvStatus;
        ImageView ivTime1;
        TextView tvNomeTime1;
        TextView tvGoalsTime1;
        RecyclerView rvTime1;
        ImageView ivTime2;
        TextView tvGoalsTime2;
        TextView tvNomeTime2;
        RecyclerView rvTime2;

        ViewHolder(View itemView) {
            super(itemView);

            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            ivTime1 = (ImageView) itemView.findViewById(R.id.iv_time_1);
            tvNomeTime1 = (TextView) itemView.findViewById(R.id.tv_nome_time_1);
            tvGoalsTime1 = (TextView) itemView.findViewById(R.id.tv_goals_time_1);
            rvTime1 = initRecyclerView( R.id.rv_goals_time_1, R.layout.item_goal_left );

            ivTime2 = (ImageView) itemView.findViewById(R.id.iv_time_2);
            tvGoalsTime2 = (TextView) itemView.findViewById(R.id.tv_goals_time_2);
            tvNomeTime2 = (TextView) itemView.findViewById(R.id.tv_nome_time_2);
            rvTime2 = initRecyclerView( R.id.rv_goals_time_2, R.layout.item_goal_right );
        }

        private RecyclerView initRecyclerView( int rvId, int idLayout ){
            RecyclerView rv = (RecyclerView) itemView.findViewById( rvId );
            LinearLayoutManager mLayoutManager = new LinearLayoutManager( context );
            mLayoutManager.setAutoMeasureEnabled(true);
            rv.setLayoutManager(mLayoutManager);
            rv.setAdapter( new GoalsAdapter(context, idLayout) );
            return rv;
        }

        private void setData( Jogo jogo ){
            tvStatus.setText(
                    Html.fromHtml( "<b>"+jogo.getStatus()+"</b> ("+jogo.getInicio()+")" ) );

            Picasso.with( context )
                    .load( jogo.getTime1().getImagemUrl() )
                    .into( ivTime1 );
            tvNomeTime1.setText( String.valueOf( jogo.getTime1().getNome() ) );
            tvGoalsTime1.setText( String.valueOf( jogo.getTime1().getGoals() ) );
            updateRecyclerView( rvTime1, jogo.getTime1().getGoalsLista() );

            Picasso.with( context )
                    .load( jogo.getTime2().getImagemUrl() )
                    .into( ivTime2 );
            tvNomeTime2.setText( String.valueOf( jogo.getTime2().getNome() ) );
            tvGoalsTime2.setText( String.valueOf( jogo.getTime2().getGoals() ) );
            updateRecyclerView( rvTime2, jogo.getTime2().getGoalsLista() );
        }

        private void updateRecyclerView( RecyclerView rv, List<Goal> goals ){
            GoalsAdapter adapter = (GoalsAdapter) rv.getAdapter();
            adapter.setGoasl( goals );
        }
    }



    public JogosAdapter(Context context, List<Jogo> jogos){
        this.context = context;
        this.jogos = jogos;
    }

    @Override
    public JogosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate(R.layout.item_jogo, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( jogos.get( position ) );
    }

    @Override
    public int getItemCount() {
        return jogos.size();
    }
}
