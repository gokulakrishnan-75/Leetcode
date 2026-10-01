class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String,List<String>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        if(!wordList.contains(endWord)) return 0;
        if(!wordList.contains(beginWord)) wordList.add(beginWord);

        for(String word: wordList){
            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0,i) + "*" + word.substring(i+1);
                graph.putIfAbsent(pattern, new ArrayList<>());
                graph.get(pattern).add(word);
            }
        }

        Queue<String> bfs = new LinkedList<>();
        bfs.add(beginWord);
        visited.add(beginWord);
        int ans = 1;

        while(!bfs.isEmpty()){
            int size = bfs.size();
            for(int k=0; k<size; k++){
                String word = bfs.poll();
                for(int i=0; i<word.length(); i++){
                    String pattern = word.substring(0,i) + "*" + word.substring(i+1);
                    for(String s: graph.get(pattern)){
                        if(s.equals(endWord)) return ans+1;
                        if(!visited.contains(s)){
                            bfs.add(s);
                            visited.add(s); 
                        }
                    }
                    graph.get(pattern).clear();
                }
            }
            ans++;
        }
        return 0;
    }
}