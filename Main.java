import java.util.*;

public class Main {
    static List<Album> albums = new ArrayList<>();
    static LinkedList<Cancion> playlist = new LinkedList<>();
    private static ListIterator<Cancion> playlistIterator;
    static boolean cambiar = true;

    public static void main(String[] args) {
        Album album1 = new Album("Greatest Hits", "Queen");
        album1.addSong("Bohemian Rhapsody", 6.03);
        album1.addSong("Another One Bites the Dust", 3.36);
        album1.addSong("We Will Rock You", 2.02);
        albums.add(album1);


        Album album2 = new Album("Back in Black", "AC/DC");
        album2.addSong("Back in Black", 4.15);
        album2.addSong("You Shook Me All Night Long", 3.30);
        album2.addSong("Hells Bells", 5.12);
        albums.add(album2);

        album1.addToPlayList("Bohemian Rhapsody", playlist);
        album1.addToPlayList("We Will Rock You", playlist);
        album2.addToPlayList(2, playlist);

        printPlaylist();

        play();

    }

    private static void printPlaylist() {
        System.out.println("Lista de Reproducción:");
        ListIterator<Cancion> iterator = playlist.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void printMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("0. Salir de la lista de reproduccion");
        System.out.println("1. Reproducir siguiente cancion de la lista");
        System.out.println("2. Reproducir la canción previa de la lista");
        System.out.println("3. Repetir la canción actual");
        System.out.println("4. Imprimir la lista de canciones en la playlist");
        System.out.println("5. Volver a imprimir el menú.");
    }

    private static void play() {
        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;
        playlistIterator = playlist.listIterator();

        if (playlist.isEmpty()) {
            System.out.println("No hay canciones en la playlist");
        } else {
            System.out.println("Reproduciendo: " + playlistIterator.next());
        }
        int eleccion;
        printMenu();


        while (seguir) {
            System.out.println("Ingrese una opcion");
            eleccion = scanner.nextInt();

            switch (eleccion) {
                case 0:
                    seguir = false;
                    break;
                case 1:
                    playSiguiente();
                    break;
                case 2:
                    playAnterior();
                    break;
                case 3:
                    repetirCancion();
                    break;
                case 4:
                    printPlaylist();
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    eliminarCancion();
                    break;
            }
        }
    }

    private static void playSiguiente() {
        if (playlistIterator.hasNext() && playlistIterator.hasPrevious()) {
            System.out.println("Reproduciendo siguiente cancion: " + playlistIterator.next());
            cambiar = true;
        } else if (playlistIterator.hasNext()) {
            playlistIterator.next();
            System.out.println("Reproduciendo siguiente cancion: " + playlistIterator.next());
            cambiar = true;
        } else {
            System.out.println("Estas al final de la playlist");
            cambiar = true;
        }
    }

    private static void playAnterior() {
        if (playlistIterator.hasPrevious()) {
            playlistIterator.previous();
            if (playlistIterator.hasPrevious()) {
                System.out.println("Reproduciendo cancion anterior: " + playlistIterator.previous());
            } else {
                System.out.println("Reproduciendo cancion anterior: " + playlistIterator.next());
                playlistIterator.previous();
            }
            cambiar = true;
        } else {
            System.out.println("Ya estás al inicio de la lista de reproducción.");
            cambiar = true;
        }
    }

    private static void repetirCancion() {
        if (cambiar) {
            if (playlistIterator.hasPrevious()) {
                playlistIterator.previous();
                System.out.println("Reproduciendo la cancion actual otra vez: " + playlistIterator.next());
            } else {
                playlistIterator.next();
                System.out.println("Reproduciendo la cancion actual otra vez: " + playlistIterator.previous());
            }
        }
    }
    private static void eliminarCancion() {
        if (!playlist.isEmpty()) {
            playlistIterator.remove();
            System.out.println("Canción eliminada de la lista de reproducción.");
            if (cambiar && playlistIterator.hasNext()) {
                System.out.println("Reproduciendo siguiente canción: " + playlistIterator.next());
            } else if (cambiar && playlistIterator.hasPrevious()) {
                System.out.println("Reproduciendo siguiente canción: " + playlistIterator.previous());
            } else {
                System.out.println("Ya no hay canciones en la playlist");
            }
        } else {
            System.out.println("Ya no hay nada que eliminar");
        }
    }
}
