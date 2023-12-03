package editor.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soundboard.model.catalogue.Group;
import soundboard.model.catalogue.Playlist;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Confirm the EditableGroup only mutates the wrapped Group.
 * when requested.
 *
 * @see EditableGroup
 * @see Group
 */
class EditableGroupTest {

    Group group;
    EditableGroup editableGroup;
    Playlist playlist;

    @BeforeEach
    void setUp() {
        group = new Group("test");
        editableGroup = new EditableGroup(group);
        playlist = new Playlist("mock");
    }

    @Test
    void addPlaylist() {
        editableGroup.addPlaylist(playlist);
        assertEquals(0, group.size());

        editableGroup.saveChanges();
        assertEquals(1, group.size());

        editableGroup.saveChanges();
        assertEquals(1, group.size(), "Changes should only be saved once.");
    }

    @Test
    void removePlaylist() {
        group.add(playlist);

        editableGroup.removePlaylist(playlist);
        assertEquals(1, group.size());

        editableGroup.saveChanges();
        assertEquals(0, group.size(), "Changes should only be saved once.");

        editableGroup.saveChanges();
        assertEquals(0, group.size(), "Changes should only be saved once.");
    }

    @Test
    void updateTitle() {
        editableGroup.updateTitle("dummy");
        assertEquals("test", group.getName());

        editableGroup.saveChanges();
        assertEquals("dummy", group.getName());

        editableGroup.updateTitle("foo");
        editableGroup.clearChanges();
        assertEquals("dummy", group.getName());
    }

    @Test
    void isRecentlyAdded() {
        assertFalse(editableGroup.isRecentlyAdded(playlist));
        editableGroup.addPlaylist(playlist);
        assertTrue(editableGroup.isRecentlyAdded(playlist));

        editableGroup.clearChanges();
        assertFalse(editableGroup.isRecentlyAdded(playlist));
    }

    @Test
    void isMarkedForRemoval() {
        assertFalse(editableGroup.isMarkedForRemoval(playlist));
        editableGroup.removePlaylist(playlist);
        assertTrue(editableGroup.isMarkedForRemoval(playlist));

        editableGroup.clearChanges();
        assertFalse(editableGroup.isMarkedForRemoval(playlist));
    }
}