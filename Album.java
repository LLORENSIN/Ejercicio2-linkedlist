import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    String nombre;
    String artista;
    ArrayList<Cancion> canciones;

    public Album(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }
    private Cancion findSong (String titulo){
        for(Cancion cancion : canciones){
            if(cancion.getTitulo().equals(titulo)){
                return cancion;
            }
        }
        return null;
    }
    public boolean addSong(String titulo, double duracion){
        if (findSong(titulo) == null){
            canciones.add(new Cancion(titulo,duracion));
            return true;
        }
        return false;
    }
    public boolean addToPlayList(int pista, LinkedList<Cancion> playlist){
        int index = pista -1;
        if(index >= 0 && index < canciones.size()){
            playlist.add(canciones.get(index));
            return true;
        }
        return false;
    }
    public boolean addToPlayList(String titulo,LinkedList<Cancion> playlist){
        Cancion cancion = findSong(titulo);
        if (cancion != null){
            playlist.add(cancion);
            return true;
        }
        return false;
    }
}
