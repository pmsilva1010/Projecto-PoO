/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.util.*;

/**Classe que cria o ambiente, que recebe um arraylist com as configurações
 * da matriz, agentes e objectos
 *
 * @author: Pedro Silva - 2007183130
 */
public class Ambiente {
    private ArrayList<Objecto> objectos=new ArrayList<Objecto>();
    private ArrayList<Agente> agentes=new ArrayList<Agente>();
    
    private int[] matriz=new int[2];
    private int tempo_simulacao;
    
    public Ambiente(ArrayList<String> config) throws NumberFormatException{
        matriz[0]=Integer.parseInt(config.get(0));
        matriz[1]=Integer.parseInt(config.get(1));
        tempo_simulacao=Integer.parseInt(config.get(2));
            
        for(int i=3;i<config.size();){
            if(config.get(i).compareTo("objecto")==0){
                objectos.add(new Objecto(config.get(i+1),config.get(i+2),Integer.parseInt(config.get(i+3)),Integer.parseInt(config.get(i+4)),config.get(i+5)));
                i=i+6;
            }
            else if(config.get(i).compareTo("agente1")==0){
                agentes.add(new Agente1(config.get(i+1),config.get(i+2),Integer.parseInt(config.get(i+3)),Integer.parseInt(config.get(i+4)),Integer.parseInt(config.get(i+5))));
                i=i+6;
            }
            else if(config.get(i).compareTo("agente2")==0){
                agentes.add(new Agente2(config.get(i+1),config.get(i+2),Integer.parseInt(config.get(i+3)),Integer.parseInt(config.get(i+4)),Integer.parseInt(config.get(i+5))));
                i=i+6;
            }
            else if(config.get(i).compareTo("agente3")==0){
                agentes.add(new Agente3(config.get(i+1),config.get(i+2),Integer.parseInt(config.get(i+3)),Integer.parseInt(config.get(i+4)),Integer.parseInt(config.get(i+5))));
                i=i+6;
            }
        }
    }
    
    /**Método que corre a simulação, percorrendo a lista de agentes e chamando
     * o método estratégia de cada um.
     * No fim calcula as distancias percorridas, objectos aprendidos e
     * objectos diferentes
     */
    public void corre_simulacao(){
        for(int i=0;i<tempo_simulacao;i++){
            for(int j=0;j<agentes.size();j++){
                agentes.get(j).estrategia(objectos, matriz[0], matriz[1]);
            }
        }
        for(int i=0;i<agentes.size();i++){
            agentes.get(i).distancia_percorrida();
            agentes.get(i).objectos_apreendidos();
            agentes.get(i).objectos_diferentes();
        }
    }
    
    /**Método que serve para mostrar as estatisticas no fim da simulação.
     * Cria uma nova interface própria para as estatisticas.
     */
    public void mostraEstatisica(){
        Estatistica_gui est=new Estatistica_gui(agentes);
        est.setVisible(true);   
    }
}
