public abstract class Sensor {
    protected String status = "ativo";
    public abstract void coletarDados();
    public String getStatus() { return status; }
}

class Lidar extends Sensor {
    @Override public void coletarDados() { System.out.println("[LIDAR] Mapeando 3D..."); }
}

class Camera extends Sensor {
    @Override public void coletarDados() { System.out.println("[CAMERA] Capturando imagem..."); }
}

class GPS extends Sensor {
    @Override public void coletarDados() { System.out.println("[GPS] Obtendo coordenadas..."); }
}
