public class Labirinto {
    private String[][] labirinto = null;
    private Coordenada entrada = null;
    private Coordenada saida = null;

    public Labirinto(int colunas, int linhas)
    {
        labirinto = new String[colunas][linhas];
    }

    public Labirinto(int colunas, int linhas, String str) throws Exception
    {
        labirinto = new String[colunas][linhas];
        /*
        if(colunas*linhas != str.length())
            throw new Exception("Colunas ou linhas foram informados de maneira ERRADA!");
         */
        this.preencher(str);
    }

    public void preencher(String str) throws Exception{
        int k = 0;
        for(int i = 0; i < labirinto[0].length; i++) {
            for (int h = 0 ;h < labirinto.length; h++){
                labirinto[h][i] = str.substring(k, k+1);
                if(str.substring(k, k + 1).equals("E"))
                {
                    if(entrada == null) {
                        this.entrada = new Coordenada(h, i);
                    }
                    else{ throw new Exception("Labirinto com mais de uma entrada!"); }
                }
                if(str.substring(k, k + 1).equals("S"))
                {
                    if(saida == null) { this.saida = new Coordenada(h, i); }
                    else{ throw new Exception("Labirinto com mais de uma saída!"); }
                }
                k++;
            }
        }
        if(entrada == null){
            throw new Exception("Labirinto sem ENTRADA!");
        }
        for(int i = 0; i < labirinto[0].length; i++){
            if(i == 0 || i == labirinto[0].length - 1){
                for(int j = 0; j < labirinto.length; j++){
                    if(!this.getPos(new Coordenada(j, i)).equals("#") && !this.getPos(new Coordenada(j, i)).equals("E") && !this.getPos(new Coordenada(j, i)).equals("S"))
                    { throw new Exception("Labirinto com erro de construção!"); }
                }
            }
            else {
                if(!this.getPos(new Coordenada(0, i)).equals("#") && !this.getPos(new Coordenada(0, i)).equals("E") && !this.getPos(new Coordenada(0, i)).equals("S"))
                { throw new Exception("Labirinto com erro de construção!"); }
                if(!this.getPos(new Coordenada(labirinto.length-1, i)).equals("#") && !this.getPos(new Coordenada(labirinto.length-1, i)).equals("E") && !this.getPos(new Coordenada(labirinto.length-1, i)).equals("S"))
                { throw new Exception("Labirinto com erro de construção!"); }
            }
        }
    }

    public String getPos(Coordenada coord){
        return this.labirinto[coord.getX()] [coord.getY()];
    }
    public Coordenada getEntrada(){ return this.entrada; }
    public void setPos(String str, Coordenada coord){
        this.labirinto[coord.getX()] [coord.getY()] = str;
    }
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;

        if(this == obj)
            return true;

        if(this.getClass() != obj.getClass())
            return false;

        Labirinto l = (Labirinto) obj;

        if(!this.labirinto.equals(l.labirinto))
            return false;

        if(this.getEntrada() != l.entrada)
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 33;

        ret = ret * 7 + this.labirinto.hashCode();
        ret = ret * 7 + this.entrada.hashCode();

        if(ret < 0)
            ret = -ret;

        return ret;
    }

    public Labirinto(Labirinto modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.labirinto = modelo.labirinto;
        this.entrada = modelo.entrada;
    }

    public Object clone()
    {
        Labirinto ret = null;

        try
        {
            ret = new Labirinto(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}
