/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import styles from './NewsBody.css';

function NewsBody({ detail }) {
  return (
    <Row>
      <Col offset={2} span={16}>
        <div>
          <p className={styles.news_title}>{detail.title}</p>
          <p className={styles.news_date}>{detail.dateToString}</p>
        </div>
        <hr className={styles.below_title} />
        <div className={styles.news_article}>
          {detail.resolvedText.map((text, i) => <p key={i}>{text}</p>)}
        </div>
        <a href={detail.url} rel="noopener noreferrer" target="_blank" className={styles.original}>
          <div className={styles.original_link} />
        </a>
      </Col>
    </Row>
  );
}

function mapStateToProps(state) {
  const { detail } = state.news;
  return { detail };
}

export default connect(mapStateToProps)(NewsBody);
