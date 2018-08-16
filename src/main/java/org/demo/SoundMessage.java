package org.demo;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class SoundMessage extends AbstractMessage {

  private String sound;
}
