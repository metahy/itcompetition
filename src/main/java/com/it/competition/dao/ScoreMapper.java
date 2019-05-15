package com.it.competition.dao;

import com.it.competition.domain.Score;
import com.it.competition.domain.ScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    long countByExample(ScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int deleteByExample(ScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int insert(Score record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int insertSelective(Score record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    List<Score> selectByExample(ScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    Score selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int updateByExampleSelective(@Param("record") Score record, @Param("example") ScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int updateByExample(@Param("record") Score record, @Param("example") ScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int updateByPrimaryKeySelective(Score record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table score
     *
     * @mbg.generated Mon May 13 16:29:39 CST 2019
     */
    int updateByPrimaryKey(Score record);
}