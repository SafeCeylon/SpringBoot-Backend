package dev.safeceylon.SafeCeylon.DMC_Dashboard;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DMCDashboard {

    private float disasterVictimStatusToReplyCount;
    private float disasterVictimStatusRepliedCount;
    private float disasterVictimStatusClosedCount;
    private int disasterOfficerCount;
    private int publicUserCount;
    private List<Map<String, Integer>> disasterMarkCounts;
}

