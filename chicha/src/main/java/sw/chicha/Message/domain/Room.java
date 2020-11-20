package sw.chicha.Message.domain;

import lombok.*;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 기본 생성자 (protected type)
@Setter
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer roomNumber;
    private String roomName;

    @Builder
    public Room(Long id, Integer roomNumber, String roomName) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomName + ", roomName=" + roomName + "]";
    }

}
