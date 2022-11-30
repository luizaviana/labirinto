import java.awt.image.ComponentColorModel;
import java.io.*;

public class Program {
    public static void main(String args[]) throws Exception {

        System.out.println("Digite o nome do arquivo: ");
        String str = Teclado.getUmString();
        Leitor leitor = new Leitor(str);
        Pilha<Coordenada> caminho = new Pilha<Coordenada>();
        Pilha<Coordenada> bifurcacoes = new Pilha<Coordenada>();
        ListaSimplesDesordenada opcoes = new ListaSimplesDesordenada<Coordenada>();
        Coordenada pos;

        int linhas = leitor.getLinhas();
        int colunas = leitor.getColunas();

        String arq = leitor.ler().substring(4);
        Labirinto labirinto = new Labirinto(colunas, linhas, arq);

        String ret = "";
        Coordenada coord = null;

        caminho.guardeUmItem(labirinto.getEntrada())  ;

        System.out.println("");
        for(int i = 0; i < linhas; i++){
            for(int k = 0; k < colunas; k++){
                coord = new Coordenada(k,i);
                ret += labirinto.getPos(coord);
            }
            System.out.println(ret);
            ret = "";
        }

        boolean completou = false;
        while (true) {
            Coordenada atual = new Coordenada((Coordenada) caminho.recupereUmItem());

            if (atual.getY() > 0) {
                Coordenada norte = new Coordenada(atual.getX(), atual.getY() - 1);
                if (labirinto.getPos(norte).equals("S")) {
                    caminho.guardeUmItem(norte);
                    completou = true;
                    break;
                }
                else if (labirinto.getPos(norte).equals(" ")) {
                    opcoes.guardeUmItemNoInicio(norte);
                }

            }

            if (atual.getY() < linhas - 1) {
                Coordenada sul = new Coordenada(atual.getX(), atual.getY() + 1);
                if (labirinto.getPos(sul).equals("S")) {
                    caminho.guardeUmItem(sul);
                    completou = true;
                    break;
                }
                else if (labirinto.getPos(sul).equals(" ")) {
                    opcoes.guardeUmItemNoInicio(sul);
                }

            }

            if (atual.getX() > 0) {
                Coordenada oeste = new Coordenada(atual.getX() - 1, atual.getY());
                if (labirinto.getPos(oeste).equals("S")) {
                    caminho.guardeUmItem(oeste);
                    completou = true;
                    break;
                }
                else if (labirinto.getPos(oeste).equals(" ")) {
                    opcoes.guardeUmItemNoInicio(oeste);
                }

            }

            if (atual.getX() < colunas - 1) {
                Coordenada leste = new Coordenada(atual.getX() + 1, atual.getY());
                if (labirinto.getPos(leste).equals("S")) {
                    caminho.guardeUmItem(leste);
                    completou = true;
                    break;
                }
                else if (labirinto.getPos(leste).equals(" ")) {
                    opcoes.guardeUmItemNoInicio(leste);
                }
            }

            if(opcoes.getQuantidade()>1){ // bifurcação
                bifurcacoes.guardeUmItem(atual);
                opcoes.removaItemDoInicio();
                caminho.guardeUmItem((Coordenada) opcoes.recupereItemDoInicio());
                labirinto.setPos("*", (Coordenada) opcoes.recupereItemDoInicio());
                opcoes.removaItemDoInicio();
            }
            else if(opcoes.getQuantidade()==1){ // apenas uma opção
                caminho.guardeUmItem((Coordenada) opcoes.recupereItemDoInicio());
                labirinto.setPos("*", (Coordenada) opcoes.recupereItemDoInicio());
                opcoes.removaItemDoInicio();
            }
            else if(opcoes.getQuantidade() == 0){ // sem saída
                if(bifurcacoes.isVazia()) {
                    System.out.println("Labirinto sem saída");
                    break;
                }
                else {
                    do{
                        if(((Coordenada) caminho.recupereUmItem()).getX() == ((Coordenada) bifurcacoes.recupereUmItem()).getX() + 1 || ((Coordenada) caminho.recupereUmItem()).getX() == ((Coordenada) bifurcacoes.recupereUmItem()).getX() - 1){
                            if(((Coordenada) caminho.recupereUmItem()).getY() == ((Coordenada) bifurcacoes.recupereUmItem()).getY()){
                                labirinto.setPos("X", atual);
                            }
                            else {
                                labirinto.setPos(" ", atual);
                            }
                        }
                        else if(((Coordenada) caminho.recupereUmItem()).getY() == ((Coordenada) bifurcacoes.recupereUmItem()).getY() + 1 ||((Coordenada) caminho.recupereUmItem()).getY() == ((Coordenada) bifurcacoes.recupereUmItem()).getY() - 1){
                            if(((Coordenada) caminho.recupereUmItem()).getX() == ((Coordenada) bifurcacoes.recupereUmItem()).getX()){
                                labirinto.setPos("X", atual);
                            }
                            else {
                                labirinto.setPos(" ", atual);
                            }
                        }
                        else {
                            labirinto.setPos(" ", atual);
                        }

                        caminho.removaUmItem();

                        atual = new Coordenada((Coordenada) caminho.recupereUmItem());

                        System.out.println("");
                        for(int i = 0; i < linhas; i++){
                            for(int k = 0; k < colunas; k++){
                                coord = new Coordenada(k,i);
                                ret += labirinto.getPos(coord);
                            }
                            System.out.println(ret);
                            ret = "";
                        }

                    } while (!caminho.recupereUmItem().equals(bifurcacoes.recupereUmItem()));
                    caminho.guardeUmItem(bifurcacoes.recupereUmItem());
                    bifurcacoes.removaUmItem();
                }
            }

            System.out.println("");
            for(int i = 0; i < linhas; i++){
                for(int k = 0; k < colunas; k++){
                    coord = new Coordenada(k,i);
                    ret += labirinto.getPos(coord);
                }
                System.out.println(ret);
                ret = "";
            }
        }

        if(completou){
            System.out.println("O programa achou a saída do labirinto na coordenada: " + caminho.recupereUmItem());
        }
    }
}