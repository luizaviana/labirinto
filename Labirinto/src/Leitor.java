import java.io.*;

public class Leitor {

    private String arq;
    private int linhas;  // Linhas escritas no arquivo texto
    private int colunas; // Colunas escritas no arquivo texto

    BufferedReader in = null;
    public Leitor(String caminho) throws Exception {
        in = new BufferedReader(new FileReader("C:\\Labirinto\\"+ caminho));
        linhas = Integer.parseInt(in.readLine());
        colunas = Integer.parseInt(in.readLine());
    }

    public String ler() throws Exception {
        int linhasQnt = 0; // Quantidade de linhas que há realmente no arquivo texto

        String str;
        while((str = in.readLine()) != null) {

            if(str.length() > colunas)
                throw new Exception("[ERRO] Quantidade a MAIS de COLUNAS");
            else if(str.length() < colunas)
                throw new Exception("[ERRO] Quantidade a MENOS de COLUNAS");

            arq += str;
            linhasQnt++;
        }

        if(linhasQnt > linhas)
            throw new Exception("[ERRO] Quantidade a MAIS de LINHAS");
        else if(linhasQnt < linhas)
            throw new Exception("[ERRO] Quantidade a MENOS de LINHAS");

        return arq;
    }

    public String lerLinha() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
    }

    // getters
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Leitor lei = (Leitor) obj;

        if(!this.arq.equals(lei.arq))
            return false;

        if(this.getLinhas() != lei.getLinhas())
            return false;

        if(this.getColunas() != lei.getColunas())
            return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 2;

        ret = ret * 7 + this.getLinhas();
        ret = ret * 7 + this.getColunas();
        ret = ret * 7 + this.arq.hashCode();

        if(ret < 0)
            ret = -ret;

        return ret;
    }

    public Leitor(Leitor modelo) throws Exception{

        if(modelo == null)
            throw new Exception("modelo ausente");

        this.linhas = modelo.getLinhas();
        this.colunas = modelo.getColunas();
        this.arq = modelo.arq;
    }

    public Object clone()
    {
        Leitor ret = null;

        try
        {
            ret = new Leitor(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}
