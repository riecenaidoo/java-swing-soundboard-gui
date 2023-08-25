package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        CatalogueSelectorPanel catalogueSelector = CatalogueSelectorPanel.getCatalogueSelector();
        catalogueSelector.loadUI("src/main/resources/mock_catalogue.json");
        panel.add(catalogueSelector);

        JPanel mediaPanel = new JPanel();
        mediaPanel.add(doMiniplayer());
        mediaPanel.add(doChannelSelector());

        panel.add(mediaPanel);

        //Create and set up the window.
        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static ChannelSelectorPanel doChannelSelector() {
        ChannelSelectorPanel panel = ChannelSelectorPanel.getChannelSelector();
        panel.populateChannelList(new String[]{"0", "1", "2", "3", "7"});
        return panel;
    }

    static MiniplayerPanel doMiniplayer() {
        MiniplayerPanel panel = MiniplayerPanel.getMiniplayerPanel();
        panel.setSong("Za Song");
        panel.setPlaylist("Za Playlist");
        panel.setVolume(-1);
        panel.setVolume(101);
        panel.setVolume(50);
        return panel;
    }
}
