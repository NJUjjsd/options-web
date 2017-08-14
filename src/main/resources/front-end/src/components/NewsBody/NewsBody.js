/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import styles from './NewsBody.css';

function NewsBody(props) {
  const paragraphList = props.paragraphs.split('\r\n   \r\n');
  const finalParagraphList = [];
  for (let i = 0; i < paragraphList.length; i += 1) {
    finalParagraphList[i] = <p>{paragraphList[i]}</p>;
  }
  return (
    <Row>
      <Col offset={4} span={15}>
        <div className={styles.news_title}>
          <p className={styles.news_heading}>{props.heading}</p>
          <p className={styles.news_source}>{props.source}</p>
        </div>
        <hr className={styles.below_title} />
        <div className={styles.news_article}>
          {finalParagraphList}
        </div>
        <a href={props.original} rel="noopener noreferrer" target="_blank" className={styles.original}>
          <div className={styles.original_link} /></a>
      </Col>
    </Row>
  );
}
export default connect()(NewsBody);
