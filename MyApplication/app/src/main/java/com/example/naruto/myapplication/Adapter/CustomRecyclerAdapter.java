package com.example.naruto.myapplication.Adapter;

/**
 * Created by naruto on 21/2/17.
 */

/**
 * Created by Naruto on 12/21/2016.
 */

        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.RelativeLayout;
        import android.widget.TextView;


        import com.example.naruto.myapplication.Keys.Query_data;
        import com.example.naruto.myapplication.MailServer;
        import com.example.naruto.myapplication.MainActivity;
        import com.example.naruto.myapplication.R;

        import java.math.BigDecimal;
        import java.util.ArrayList;

/**
 * Created by Naruto on 7/6/2016.
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private int pager;
    Context context;
    boolean question=true;
    private ArrayList<Query_data> query_data = new ArrayList<Query_data>();

    public CustomRecyclerAdapter(Context context, int pager) {
        layoutInflater = LayoutInflater.from(context);
        this.pager = pager;
        this.context = context;
    }


    public void setForcastList(ArrayList<Query_data> data) {
        query_data = data;
        notifyItemRangeChanged(0, query_data.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row, parent,false);
        ViewHolder viewHolderWeatherForcast = new ViewHolder(view, new ViewHolder.ViewHolderInterface() {
            @Override
            public void showDetails(View v, int position) {
            }
        });
        return viewHolderWeatherForcast;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Query_data var1=query_data.get(position);
            String s=var1.getQuestion();
            holder.text1.setText(var1.getQuestion());
            var1.setAnswer(MailServer.extractAttachmentSearchContextFromString(s));
            holder.text2.setText(var1.getAnswer());
    }


    @Override
    public int getItemCount() {
        return query_data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //define all the widgets ids
        RelativeLayout row_layout;
        RelativeLayout.LayoutParams relative_pramas;
        TextView text1;
        TextView text2;
        ViewHolderInterface myclickhandler;
        public ViewHolder(View itemView, ViewHolderInterface m) {
            super(itemView);
            itemView.setOnClickListener(this);
            row_layout=(RelativeLayout)itemView.findViewById(R.id.row_layout);
            text1=(TextView)itemView.findViewById(R.id.text1);
            text2=(TextView)itemView.findViewById(R.id.text2);
            relative_pramas = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            myclickhandler=m;
        }

        @Override
        public void onClick(View v) {
            myclickhandler.showDetails(v, getAdapterPosition());
        }

        interface ViewHolderInterface {
            public void showDetails(View v, int position);
        }
    }


}


