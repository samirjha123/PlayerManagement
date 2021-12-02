package com.sports.mapper;

import java.io.Serializable;

import com.sports.entity.Score;
import com.sports.model.ScoreModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObjectMapper extends Serializable {

    public ObjectMapper OBJECT_MAPPER = Mappers.getMapper(ObjectMapper.class);

    public Score scoreModelToScore(ScoreModel scoreModel);

    public ScoreModel scoreToScoreModel(Score score);
}
