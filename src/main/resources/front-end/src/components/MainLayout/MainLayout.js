/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout } from 'antd';
import styles from './MainLayout.css';
import Nav from '../../components/Nav/Nav';
import Footer from '../../components/Footer/Footer';

function MainLayout({ children }) {
  return (
    <Layout className={styles.normal}>
      <Nav />
      {children}
      <Footer />
    </Layout>
  );
}

export default MainLayout;
