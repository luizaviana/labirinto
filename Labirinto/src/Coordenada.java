public class Coordenada{
    private int X;
    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) throws Exception{
        if(x < 0)
            throw new Exception("Valor de coordenada inválido");
        this.X = x;
    }

    public void setY(int y) throws Exception{
        if(y < 0)
            throw new Exception("Valor de coordenada inválido");
        this.Y = y;
    }

    public Coordenada(int x,int y){
        this.X = x;
        this.Y = y;
    }

    public Coordenada(Coordenada coord){
        this.X = coord.getX();
        this.Y = coord.getY();
    }

    public String toString(){
        return "(" + X + ", " + Y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Coordenada coord = (Coordenada)obj;

        if(this.getX() != coord.getX()) return false;

        if(this.getY() != coord.getY()) return false;

        return true;
    }

    public int hashCode()
    {
        int ret = 2;

        ret = ret * 7 + this.getX();
        ret = ret * 7 + this.getY();

        if(ret < 0)
            ret = -ret;

        return ret;
    }

    public Object clone()
    {
        Coordenada ret = null;

        try
        {
            ret = new Coordenada(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}