package at.tintildev.gardenpomodorotimer.domain;

import java.util.ArrayList;

public class Tags {
    public final ArrayList<String> tags;
    public static Tags instance;

    private Tags(){
        tags = new ArrayList<>();
        tags.add("lernen");

    }

    public static Tags getInstance() {
        if (instance == null) {
            instance = new Tags();
        }
        return instance;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void addTags(String tag){
        if(tag != null && !tag.isEmpty() && !tags.contains(tag)) {
            tags.add(tag);
        } else {
            System.out.println("Tag is null, empty or already exists: " + tag);
        }
    }

    public void removeTag(String tag){
        if(tag != null && !tag.isEmpty() && tags.contains(tag)) {
            tags.remove(tag);
        } else {
            System.out.println("Tag is null, empty or does not exist: " + tag);
        }
    }

    /** Update an existing tag with a new value.
     *
     * @param tag    The tag to be updated.
     * @param newTag The new value for the tag.
     */
    public void updateTag(String tag, String newTag){
        System.out.println(tags);
        System.out.println("Updating tag: " + tag + " to new tag: " + newTag);
        int index = tags.indexOf(tag);
        System.out.println("Index of tag to update: " + index);
        if(index != -1 && newTag != null && !newTag.isEmpty() && !tags.contains(newTag)) {
            tags.set(index, newTag);
        } else {
            System.out.println("Tag to update not found, new tag is null, empty or already exists: " + newTag);
        }
    }


}
