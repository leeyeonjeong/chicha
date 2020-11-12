package sw.chicha.Message.dto;

import lombok.*;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Message.domain.Room;
import sw.chicha.Schedule.domain.Schedule;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomDto {

    private Long id;
    private String name;

    public Room toEntity() {
        return Room.builder()
                .id(id)
                .name(name)
                .build();
    }

    @Builder
    public RoomDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
