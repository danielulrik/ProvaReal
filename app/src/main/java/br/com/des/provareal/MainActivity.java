package br.com.des.provareal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final double PRECO_BASE = 2.7;
    private static final double VALOR_MINUTO = 0.2;
    private static final double VALOR_KM = 1.48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tempo = obterValor(((EditText) findViewById(R.id.edit_text_tempo)).getText().toString());
                double distancia = obterValor(((EditText) findViewById(R.id.edit_text_distancia)).getText().toString());
                if (tempo > 0 && distancia > 0) {
                    ((TextView) findViewById(R.id.text_view_preco_base)).setText(formataValor(PRECO_BASE));
                    ((TextView) findViewById(R.id.text_view_preco_distancia)).setText(formataValor(VALOR_KM * distancia));
                    ((TextView) findViewById(R.id.text_view_tempo)).setText(formataValor(VALOR_MINUTO * tempo));
                    double total = PRECO_BASE + (VALOR_MINUTO * tempo) + (VALOR_KM * distancia);
                    ((TextView) findViewById(R.id.text_view_valor_total)).setText(formataValor(total));
                    ((TextView) findViewById(R.id.text_view_valor_a_receber)).setText(formataValor(total - (total * 0.25)));
                } else {
                    Toast.makeText(getApplicationContext(), "Favor informar os valores", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String formataValor(double valor) {
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    private double obterValor(String valor) {
        if (valor.isEmpty()) return 0;
        return Double.parseDouble(valor);
    }
}
