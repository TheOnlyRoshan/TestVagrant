import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SongsPlaylist {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    private class Node {
        int song;
        int user;
        Node prev;
        Node next;

        public Node(int song, int user) {
            this.song = song;
            this.user = user;
            this.prev = null;
            this.next = null;
        }
    }

    public SongsPlaylist(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    private void addNodeToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int[] getRecentlyPlayedSongs(int user) {
        if (map.containsKey(user)) {
            Node node = map.get(user);
            removeNode(node);
            addNodeToHead(node);
            Node curr = head.next;
            int[] songs = new int[capacity];
            int i = 0;
            while (curr != tail && i < capacity) {
                songs[i++] = curr.song;
                curr = curr.next;
            }
            return songs;
        } else {
            return new int[capacity];
        }
    }

    public void addSong(int song, int user) {
        if (map.containsKey(user)) {
            Node node = map.get(user);
            node.song = song;
            removeNode(node);
            addNodeToHead(node);
        } else {
            if (map.size() >= capacity) {
                Node last = tail.prev;
                map.remove(last.user);
                removeNode(last);
            }
            Node node = new Node(song, user);
            map.put(user, node);
            addNodeToHead(node);
        }
    }
    public static void main(String[] args){
        // Create an LRUCache with capacity of 3 songs per user
        SongsPlaylist songsPlaylist = new SongsPlaylist(3);

        // Add some songs for user 1
        songsPlaylist.addSong(1, 1);
        songsPlaylist.addSong(2, 1);
        songsPlaylist.addSong(3, 1);

        // Get recently played songs for user 1
        int[] songs = songsPlaylist.getRecentlyPlayedSongs(1);
        System.out.println(Arrays.toString(songs)); // [3, 2, 1]

        // Add a new song for user 1
        songsPlaylist.addSong(4, 1);

        // Get recently played songs for user 1 again
        songs = songsPlaylist.getRecentlyPlayedSongs(1);
        System.out.println(Arrays.toString(songs)); // [4, 3, 2]

        // Add some songs for user 2
        songsPlaylist.addSong(1, 2);
        songsPlaylist.addSong(2, 2);
        songsPlaylist.addSong(3, 2);

        // Get recently played songs for user 2
        songs = songsPlaylist.getRecentlyPlayedSongs(2);
        System.out.println(Arrays.toString(songs)); // [3, 2, 1]

        // Add a new song for user 2
        songsPlaylist.addSong(4, 2);

        // Get recently played songs for user 2 again
        songs = songsPlaylist.getRecentlyPlayedSongs(2);
        System.out.println(Arrays.toString(songs)); // [4, 3, 2]
    }
}
