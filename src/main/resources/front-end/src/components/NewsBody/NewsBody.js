/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import styles from './NewsBody.css';

function NewsBody({ detail }) {
  const paragraphList = detail.text.split('\r\n   \r\n');
  const paragraphs = [];
  for (let i = 0; i < paragraphList.length; i += 1) {
    paragraphs[i] = <p>{paragraphList[i]}</p>;
  }

  return (
    <Row>
      <Col offset={4} span={16}>
        <div>
          <p className={styles.news_title}>{detail.title}</p>
          <p className={styles.news_date}>{detail.dateToString}</p>
        </div>
        <hr className={styles.below_title} />
        <div className={styles.news_article}>{paragraphs}</div>
        <a href={detail.url} rel="noopener noreferrer" target="_blank" className={styles.original}>
          <div className={styles.original_link} />
        </a>
      </Col>
    </Row>
  );
}

NewsBody.prototypes = {
  detail: Object,
};

function mapStateToProps(state) {
  const { detail } = state.news;
  return { detail };
}

export default connect(mapStateToProps)(NewsBody);
