package br.ulbra.calculadoraenergia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Nomeação das variaveis
    EditText edAparelho, edPotencia, edTempo, edPreco;
    Button btCalcular, btLimpar;
    TextView txRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txRes = findViewById(R.id.txtRes);
        edAparelho = findViewById(R.id.edtAparelho);
        edPotencia = findViewById(R.id.edtPotencia);
        edPreco = findViewById(R.id.edtPreco);
        edTempo = findViewById(R.id.edtTempo);
        btLimpar = findViewById(R.id.btnLimpar);
        btCalcular = findViewById(R.id.btnCalcular);
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (
                        edAparelho.getText().toString().isEmpty() ||
                                edPotencia.getText().toString().isEmpty() ||
                                edPreco.getText().toString().isEmpty() ||
                                edTempo.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this, "campos vazios",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                String aparelho = edAparelho.getText().toString();
                double potencia = Double.parseDouble(edPotencia.getText().toString());
                double tempo = Double.parseDouble(edTempo.getText().toString());
                double preco = Double.parseDouble(edPreco.getText().toString());

                double cE = potencia * tempo / 1000;
                double C = cE * preco;

                DecimalFormat df = new DecimalFormat("0.00");
                String valorFormato = df.format(C);

                if(potencia <= 0 || tempo <= 0 || preco <= 0){
                    Toast.makeText(MainActivity.this, "Não como calcular se for zero", Toast.LENGTH_LONG).show();
                    return;
                }

                txRes.setText("Nome do aparelho é " + aparelho + "\n" +
                        "Consumo " +
                        "de energia: " + cE + "\n" +
                        "Custo financeiro: R$" + df.format(C) + "\n");
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edAparelho.setText(null);
                edTempo.setText(null);
                edPreco.setText(null);
                edPotencia.setText(null);
            }
        });


    }
}