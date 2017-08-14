/**
 * Created by a297 on 17/8/12.
 */
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import MainLayout from '../../components/MainLayout/MainLayout';
import Separate from '../../components/Separate/Separate';
import NewsBody from '../../components/NewsBody/NewsBody';
import styles from './NewsDetails.css';

function NewsDetails(props) {
  return (
    <MainLayout>
      <Row>
        <Col className={styles.cur_location} offset={4}>
          新闻 &gt; 正文</Col>
      </Row>
      <Separate />
      <NewsBody
        heading={props.heading} source={props.source}
        paragraphs={props.paragraphs} original={props.original}
      />
    </MainLayout>
  );
}

NewsDetails.propTypes = {
  heading: PropTypes.string,
  source: PropTypes.string,
  paragraphs: PropTypes.string,
  original: PropTypes.string,
};

function mapStateToProps(state) {
  const { heading, source, paragraphs, original } = state.NewsDetails;
  return { heading, source, paragraphs, original };
}

export default connect(mapStateToProps)(NewsDetails);
