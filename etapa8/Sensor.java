package service;
 
/**
 * Sensor: classe abstrata base para sensores embarcados do drone.
 * Subclasses: Lidar, Camera, GPS (diagrama de classes original).
 */
public abstract class Sensor {
 
    protected String status;
 
    public Sensor() {
        this.status = "ativo";
    }
 
    public abstract void coletarDados();
 
    public String getStatus() { return status; }
}
 
// ── Lidar ─────────────────────────────────────────────────────
class Lidar extends Sensor {
    @Override
    public void coletarDados() {
        System.out.println("[LIDAR] Mapeando ambiente 3D...");
    }
}
 
// ── Camera ────────────────────────────────────────────────────
class Camera extends Sensor {
    @Override
    public void coletarDados() {
        System.out.println("[CAMERA] Capturando imagem...");
    }
}
 
// ── GPS ───────────────────────────────────────────────────────
class GPS extends Sensor {
    @Override
    public void coletarDados() {
        System.out.println("[GPS] Obtendo coordenadas...");
    }
}
