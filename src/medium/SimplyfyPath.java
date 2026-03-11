package medium;

import java.util.Stack;

public class SimplyfyPath {

    /**
     * Stack based approach
     * if . then just remove it
     * if .. then pop from stack and trash that entry.
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path == null || path.isBlank()) {
            return path;
        }
        Stack<String> pathStack = new Stack<>();
        String[] pathParts = path.split("/");
        for (String pathEntry : pathParts) {
            if (pathEntry.equals(".") ||pathEntry.isBlank()) {
                // Ignore current directory and multiple // in the final canonical path.
                continue;
            }
            if(pathEntry.equals("..")) {
                // Parent directory actually
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                }

                continue;
            }
            pathStack.push(pathEntry);
        }

        String result = "";
        while (!pathStack.isEmpty()) {
            result = pathStack.pop() + (result == "" ? result : "/" + result);
        }

        return "/" + result;
    }

    public static void main(String[] args) {
        SimplyfyPath simplyfyPath = new SimplyfyPath();
        String path = "/home/";
        System.out.println("The canonical path is " + simplyfyPath.simplifyPath(path));
        String path1 = "/home//foo/";
        System.out.println("The canonical path is " + simplyfyPath.simplifyPath(path1));
        String path2 = "/home/user/Documents/../Pictures";
        System.out.println("The canonical path is " + simplyfyPath.simplifyPath(path2));
        String path3 = "/../";
        System.out.println("The canonical path is " + simplyfyPath.simplifyPath(path3));
        String path4 = "/.../a/../b/c/../d/./";
        System.out.println("The canonical path is " + simplyfyPath.simplifyPath(path4));
    }
}


