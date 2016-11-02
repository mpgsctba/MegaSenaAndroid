package com.loterias.caixa.megasena.WebService;

import android.content.Context;
import android.util.Log;

import com.loterias.caixa.megasena.Dao.BancoDeDados;
import com.loterias.caixa.megasena.Model.Resultado;
import com.loterias.caixa.megasena.Utils.Constante;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcos on 28/10/2016.
 */
public class Sincronismo {

    private Context context;
    private Boolean ultimoQuebrado;

    public Sincronismo(Context context, Boolean ultimoQuebrado) {
        this.context = context;
        this.ultimoQuebrado = ultimoQuebrado;
    }

    public void baixarJogos() {
        try {
            BancoDeDados bd = new BancoDeDados(context);
            WebService ws = new WebService(Constante.url);
            Map<String, String> param = new HashMap<String, String>();
            bd.abrir();
            Resultado resultado = bd.getUltimoConsurso();
            bd.fechar();
            Integer ultimoBanco = resultado.getConcursoId();
            param.put("loteria", Constante.loteria);
            param.put("token",Constante.token);
            String response = ws.webGet("", param);
            JSONObject retorno = new JSONObject(response);
            JSONObject concurso =  (JSONObject)retorno.get("concurso");
            Integer ultimoServidor = Integer.parseInt((String) concurso.get("numero"));

            if (ultimoServidor > ultimoBanco) {
                bd.inserirRetorno(retorno);
                ultimoServidor--;
            } else if (ultimoServidor.equals(ultimoBanco)) {
                if (ultimoQuebrado) {
                    JSONObject proximo_concurso = (JSONObject) retorno.get("proximo_concurso");
                    String proximoConcurso = ((String) proximo_concurso.get("data")).replaceAll("/", "-");
                    // caso o ultimo concurso ainda esteja quebrado retorna
                    if (proximoConcurso.isEmpty()){
                        return;
                    } else {
                        // caso o ultimo ja foi atualizado pelo server apaga o errado e atualiza
                        String id = String.valueOf(ultimoServidor);
                        bd.abrir();
                        bd.apagarConcurso(id);
                        bd.fechar();
                        bd.inserirRetorno(retorno);
                    }
                }
            }

            while (ultimoBanco < ultimoServidor) {
                param.put("concurso", ultimoServidor.toString());
                response = ws.webGet("", param);
                retorno = new JSONObject(response);
                bd.inserirRetorno(retorno);
                ultimoServidor--;
            }
        } catch (Exception e) {
            Log.d("LOG", e.getMessage());
        }
    }
}
