package com.example.conferenceroombooking.room;

public class DefaultMeetingScheduler {
    private DefaultMeetingScheduler singleton;

    public DefaultMeetingScheduler getInstance() {
        if (singleton == null) {
            synchronized (DefaultMeetingScheduler.class) {
                if (singleton == null) {
                    this.singleton = new DefaultMeetingScheduler();
                }
            }
        }
        return this.singleton;
    }


}
