package net.osmand.router;

import net.osmand.binary.BinaryMapRouteReaderAdapter;

import java.util.HashMap;
import java.util.Map;
import net.osmand.router.GeneralRouter.GeneralRouterProfile;
import net.osmand.util.Algorithms;

public class TurnLanesParser {

    final String TAG_SUFFIX = ":lanes";
    //GeneralRouter.GeneralRouterProfile profile;
    RouteSegmentResult segment;
    Direction direction;
    Map<String, String> fullLanesMap;

    public enum Direction {
        FORWARD,
        BACKWARD,
        ONEWAY
    }

    public TurnLanesParser(RouteSegmentResult segment) {
        //profile = routingContext.getRouter().getProfile();
        this.segment = segment;
        this.direction = detectDirection();
        fullLanesMap = parseFullLanesMap();
    }


    private Direction detectDirection() {
        if (segment.getObject().getOneway() > 0) {
            // do not detect oneway:moped, oneway:psv, etc.
            return Direction.ONEWAY;
        }
        if (segment.isForwardDirection()) {
            return Direction.FORWARD;
        } else {
            return Direction.BACKWARD;
        }
    }

    private Map<String, String> parseFullLanesMap() {
        int[] types = segment.getObject().getTypes();
        Map<String, String> res = new HashMap<>();
        for (int i = 0; i < types.length; i++) {
            BinaryMapRouteReaderAdapter.RouteTypeRule r = segment.getObject().region.quickGetEncodingRule(types[i]);
            if (r.getTag().contains(TAG_SUFFIX)) {
                res.put(r.getTag(), r.getValue());
            }
        }
        return res;
    }

    public String getTurnLanes() {
        if (direction == Direction.ONEWAY) {
            return fullLanesMap.get("turn:lanes");
        }
        if (direction == Direction.FORWARD) {
            return fullLanesMap.get("turn:lanes:forward");
        } else {
            return fullLanesMap.get("turn:lanes:backward");
        }
    }

    public String getAccessibility(GeneralRouterProfile profile) {
        String directionSuffix = direction == Direction.ONEWAY ? "" : (direction == Direction.FORWARD ? ":forward" : ":backward");
        if (profile == GeneralRouterProfile.BICYCLE) {
            String key = "bicycle:lanes" + directionSuffix;
            if (fullLanesMap.containsKey(key)) {
                String lanes = fullLanesMap.get(key);
                String[] splited = lanes.split("\\|");
                for (int i = 0; i < splited.length; i++) {
                    String t = splited[i];
                    if (Algorithms.isEmpty(t)) {
                        t = "yes";
                    }
                    splited[i] = t;
                }
                if (splited.length > 0) {
                    return String.join("|", splited);
                }
            }
        }
        return "";
    }
}
