import React from 'react';
import { connect } from 'dva';
import MainLayout from '../../components/MainLayout/MainLayout';
import styles from './IndexPage.css';
// import { Link } from 'dva/router';

function IndexPage() {
  return (
    <MainLayout>
      <div className={styles.normal}>
        <h1 className={styles.title}>Yay! Welcome to dva!</h1>
        <div className={styles.welcome} />
        <ul className={styles.list}>
          <li>To get started, edit <code>src/index.js</code> and save to reload.</li>
          <li><a href="https://github.com/dvajs/dva-docs/blob/master/v1/en-us/getting-started.md">Getting Started</a></li>
        </ul>
        <div>我不是随便加进来的</div>
        <div>我也不是随便加进来的</div>
      </div>
    </MainLayout>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
