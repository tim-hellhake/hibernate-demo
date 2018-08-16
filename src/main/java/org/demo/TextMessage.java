package org.demo;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class TextMessage extends AbstractMessage {

  private String text;
}
