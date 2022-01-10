package br.com.appclientesvip.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.appclientesvip.R;
import br.com.appclientesvip.model.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private List<Cliente> aClientes;
    private Context aContext;

    public ClienteAdapter(List<Cliente> aClientes, Context aContext) {
        this.aClientes = aClientes;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View linhaView = inflater.inflate(R.layout.linha_detalhe_cliente, parent, false);

        ViewHolder viewHolder = new ViewHolder(linhaView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolder holder, int position) {

        Cliente objDaLinha = aClientes.get(position);

        TextView txtPrimeiroNome = holder.rvPrimeiroNome;
        txtPrimeiroNome.setText(objDaLinha.getPrimeiroNome());

        TextView txtSegundoNome = holder.rvSegundoNome;
        txtSegundoNome.setText(objDaLinha.getSobreNome());

        TextView txtEmail = holder.rvEmail;
        txtEmail.setText(objDaLinha.getEmail());

        Button btnPessoaFisica = holder.rvPessoaFisica;
//        btnPessoaFisica.setText(oblDaLinha.isPessoaFisica() ? "CPF" : "CNPJ");
        try {

            btnPessoaFisica.setText(objDaLinha.isPessoaFisica() ? "CPF: " + objDaLinha.getClientePF().getCpf() : "CNPJ: " + objDaLinha.getClientePJ().getCnpj());

        }catch (Exception e){

            btnPessoaFisica.setText(objDaLinha.isPessoaFisica() ? "CPF: "  : "CNPJ: ");

        }

    }

    @Override
    public int getItemCount() {
        return aClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public Button rvPessoaFisica;
        public TextView rvPrimeiroNome, rvSegundoNome, rvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvPrimeiroNome = itemView.findViewById(R.id.rvPrimeiroNome);
            rvSegundoNome = itemView.findViewById(R.id.rvSegundooNome);
            rvPessoaFisica = itemView.findViewById(R.id.rvPessoaFisica);
            rvEmail = itemView.findViewById(R.id.rvEmail);

            Log.i(AppUtil.LOG_APP,"Nome: "+rvPrimeiroNome.getText().toString());

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Cliente clienteSelecionado = aClientes.get(position);

            if(position != RecyclerView.NO_POSITION){
                Log.i(AppUtil.LOG_APP,"CLIENT ID: " + position + "Primeiro Nome: " + clienteSelecionado.getPrimeiroNome());

                Toast.makeText(aContext, "CLIENT ID: " + position + "Primeiro Nome: " + clienteSelecionado.getPrimeiroNome(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }
}
