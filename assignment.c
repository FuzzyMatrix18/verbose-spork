#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>

using namespace std;

struct Event {
    int x;
    int type; // 1 for start, -1 for end
    string color;
    
    bool operator<(const Event& other) const {
        if (x == other.x)
            return type < other.type;
        return x < other.x;
    }
};

struct LineSegment {
    int start, end;
    string color;
};

int calculateMaxUnionLength(vector<LineSegment>& segments, string color) {
    vector<Event> events;
    for (const auto& seg : segments) {
        if (seg.color == color) {
            events.push_back({seg.start, 1, color});
            events.push_back({seg.end, -1, color});
        }
    }
    
    sort(events.begin(), events.end());
    
    int max_length = 0, current_length = 0, current_start = 0;
    int active_segments = 0;
    
    for (const auto& event : events) {
        if (event.type == 1) {
            if (active_segments == 0)
                current_start = event.x;
            active_segments++;
        } else {
            active_segments--;
            if (active_segments == 0) {
                max_length = max(max_length, event.x - current_start);
            }
        }
    }
    
    return max_length;
}

int calculateMaxUnionLengthWithColorOrder(vector<LineSegment>& segments, string color1, string color2) {
    vector<Event> events;
    for (const auto& seg : segments) {
        if (seg.color == color1 || seg.color == color2) {
            events.push_back({seg.start, 1, seg.color});
            events.push_back({seg.end, -1, seg.color});
        }
    }
    
    sort(events.begin(), events.end());
    
    int max_length = 0, current_length = 0, current_start = 0;
    int active_segments = 0;
    string current_color = "";

    for (const auto& event : events) {
        if (event.type == 1) {
            if (active_segments == 0) {
                current_start = event.x;
                current_color = event.color;
            }
            active_segments++;
        } else {
            active_segments--;
            if (active_segments == 0) {
                if (current_color == color1) {
                    // Continue with the next segment's color
                    current_color = event.color;
                    if (current_color == color2) {
                        max_length = max(max_length, event.x - current_start);
                    }
                } else {
                    current_color = "";
                }
            }
        }
    }
    
    return max_length;
}

int main() {
    int n;
    cin >> n;
    vector<LineSegment> segments(n);
    
    for (int i = 0; i < n; ++i) {
        int start, end;
        string color;
        cin >> start >> end >> color;
        segments[i] = {start, end, color};
    }
    
    // Compute maximum length for each color
    int maxRed = calculateMaxUnionLength(segments, "r");
    int maxGreen = calculateMaxUnionLength(segments, "g");
    int maxBlue = calculateMaxUnionLength(segments, "b");
    
    // Compute maximum length for color orders
    int maxRedGreen = calculateMaxUnionLengthWithColorOrder(segments, "r", "g");
    int maxGreenBlue = calculateMaxUnionLengthWithColorOrder(segments, "g", "b");
    int maxBlueRed = calculateMaxUnionLengthWithColorOrder(segments, "b", "r");
    
    cout << maxRed << endl;
    cout << maxGreen << endl;
    cout << maxBlue << endl;
    cout << maxRedGreen << endl;
    cout << maxGreenBlue << endl;
    if (maxBlueRed == 0)
    {
        cout << "nil" << endl;
    }
    else
        cout << maxBlueRed << endl;
    
    return 0;
}
