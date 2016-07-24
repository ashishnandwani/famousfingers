<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="robots" content="noindex, nofollow">

<title>Home | Famous Fingers</title>

<link rel="stylesheet" href="css/style.css">

<script src="js/jquery-1.9.1.min.js"></script>

<!--[if lt IE 9]>
<script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

</head>

<body>
<div id="wrapper">
    <div id="right_div">
    	<header id="main_header">
            <section id="user">
                <h2 id="user_name" class="user_name icon">Akhtar Ahsan</h2>
                <ul id="user_links">
                    <li><a href="an-profile.html">Profile</a></li>
                    <li><a href="login-logged_out.html">Logout</a></li>
                </ul>
            </section>
            <nav id="main_nav" class="second_style">
                <ul>
                    <li><a href="an_normal-home.html" class="active">Home</a></li>
                    <li><a href="an_normal-report.html">Report</a></li>
                </ul>
            </nav>
        </header>


        <section id="content">
            <section id="report">
                <header>
                    <h2 class="report icon">Reports <span class="subhead">11 pending reports</h2>
                    <form id="report_list_refine_form" class="header_refine_form" action="an-home.html" method="get" name="report_list_refine_form">
                        <label for="status">Reports:</label>
                        <select id="status" name="status">
                            <option value="all">All</option>
                            <option value="pending">Pending</option>
                            <option value="completed">Completed</option>
                            <option value="cancelled">Cancelled</option>
                        </select>
                        
                        <label for="sort_by">Sort by:</label>
                        <select id="sort_by" name="sort_by">
                            <option value="pending">Pending first</option>
                            <option value="date">Date</option>
                            <option value="name">Name</option>
                        </select>
                        
                        <select id="order" name="order">
                            <option value="asc">Ascending</option>
                            <option value="desc">Descending</option>
                        </select>
                        
                        <label for="name">Report of:</label>
                        <input type="search" id="name" name="name">
                        
                        <input id="report_list_refine_submit" type="submit" value="Go &rarr;" name="report_list_refine_submit">
                    </form>
                </header>
    
                <ul id="report_list" class="content_list">
                    <li id="report1" class="sent_for_analysis pending">
                        <h3>Deepika <span class="report_type">child</span></h3>
                        <p class="report_status">Sent for analysis</p>
                        <p class="meta_created" title="Created by Rahulina (XXX01234) at 17:30, Sunday, 24th March, 2013"><span class="report_consultant">Rahulina (XXX01234)</span> on <time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <div class="report_links"><a href="#" class="icon download prints_file" title="Download Deepika's fingerprints">fingerprints file name link</a></div>
                    </li>
                    <li id="report2" class="analysing pending">
                        <h3>Deepika <span class="report_type">child</span></h3>
                        <p class="report_status">Analysing</p>
                        <p class="meta_created" title="Created by Rahulina (XXX01234) at 17:30, Sunday, 24th March, 2013"><span class="report_consultant">Rahulina (XXX01234)</span> on <time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <div class="report_links"><a href="#" class="icon download prints_file" title="Download Deepika's fingerprints">fingerprints file name link</a>
                        <a href="an-home-cancellation_reason.html" class="button cancel_button disable icon" title="Cancel report"></a>
                        <a href="an-home-upload_analysis_report.html" class="button upload icon">Upload analysis</a></div>
                    </li>
                    <li id="report3" class="analysed complete">
                        <h3>Deepika <span class="report_type">child</span></h3>
                        <p class="report_status">Analysed</p>
                        <p class="meta_created" title="Created by Rahulina (XXX01234) at 17:30, Sunday, 24th March, 2013"><span class="report_consultant">Rahulina (XXX01234)</span> on <time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="meta_analysed" title="Analysed by you at 17:30, Sunday, 24th March, 2013"><time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="report_remarks"><em>Remarks:</em> Some remarks here some remarks here some remarks here</p>
                        <div class="report_links"><a href="#" class="icon download prints_file" title="Download Deepika's fingerprints">fingerprints file name link</a>
                        <a href="#" class="icon download report_file" title="Download Analysis report">report file name link</a><a href="an-home-report_removed.html#report3" class="icon remove" title="remove report"></a>
                        <a href="an-home-cancellation_reason.html" class="button cancel_button disable icon" title="Cancel report"></a></div>
                    </li>
                    <li id="report4" class="completed complete">
                        <h3>Deepika <span class="report_type">child</span></h3>
                        <p class="report_status">Completed</p>
                        <p class="meta_created" title="Created by Rahulina (XXX01234) at 17:30, Sunday, 24th March, 2013"><span class="report_consultant">Rahulina (XXX01234)</span> on <time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="meta_analysed" title="Analysed by you at 17:30, Sunday, 24th March, 2013"><time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="report_remarks"><em>Remarks:</em> Some remarks here some remarks here some remarks here</p>
                        <div class="report_links"><a href="#" class="icon download prints_file" title="Download Deepika's fingerprints">fingerprints file name link</a>
                        <a href="#" class="icon download report_file" title="Download Analysis report">report file name link</a></div>
                    </li>
                    <li id="report5" class="cancelled">
                        <h3>Deepika <span class="report_type">child</span></h3>
                        <p class="report_status">Cancelled</p>
                        <p class="meta_created" title="Created by Rahulina (XXX01234) at 17:30, Sunday, 24th March, 2013"><span class="report_consultant">Rahulina (XXX01234)</span> on <time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="meta_cancelled" title="Cancelled by you at 17:30, Sunday, 24th March, 2013"><time datetime="2013-03-24" pubdate>24th March, 2013</time></p>
                        <p class="report_remarks"><em>Reason:</em> Here comes the cancellation reason Here comes the cancellation reason</p>
                    </li>
                </ul><!-- END OF UL #REPORT_LIST -->
                
               	<ul class="page_links">
                	<li><a href="" class="button normal_button">&laquo; Previous page</a></li>
                	<li><a href="" class="button normal_button">Next page &raquo;</a></li>
                	<li class="page_no">Page 1 of 45</li>
                </ul>
            </section><!-- END OF SECTION #REPORT -->
        </section><!-- END OF SECTION #CONTENT -->
    
    
        <footer id="main_footer">
        	<div id="footer_container">
                <p><em>2057</em> consultants registered.</p>
                <p><em>12034</em> fingerprints analysed.</p>
                
                <small id="copyright">Consultant Comunication Application. <span class="dont_break">&copy; 2013 <em>FamousFingers</em>.</span></small>
            </div>
        </footer>
    </div><!-- END OF DIV #RIGHT_DIV -->


    <div id="left_div">
        <div id="logo_container">
            <a href="an-home.html"><h1 id="logo" class="hide_text">Famous Fingers</h1></a>
        </div>
        
        <aside id="content_sidebar">
            <section id="whiteboard">
                <h2 class="whiteboard icon">Whiteboard</h2>
                <ul id="whiteboard_msg_list" class="long_list">
                    <li>
                        <h3>Heading</h3>
                        <time class="datetime" datetime="2013-03-24" pubdate title="17:30, Sunday, 24th March, 2013">Sunday, 24th March, 2013</time>
                        <p>Content, messages, news, tech etc. Anything you can think of</p>
                    </li>
                    <li>
                        <h3>Heading</h3>
                        <time class="datetime" datetime="2013-03-24" pubdate title="17:30, Sunday, 24th March, 2013">Sunday, 24th March, 2013</time>
                        <p>Content, messages, news, tech etc. Anything you can think of</p>
                    </li>
                    <li>
                        <h3>Heading</h3>
                        <time class="datetime" datetime="2013-03-24" pubdate title="17:30, Sunday, 24th March, 2013">Sunday, 24th March, 2013</time>
                        <p>Content, messages, news, tech etc. Anything you can think of</p>
                    </li>
                </ul>
            </section><!-- END OF SECTION #WHITEBOARD-->
        </aside><!-- END OF ASIDE #CONTENT_SIDEBAR -->
    </div><!-- END OF DIV #LEFT_DIV -->
</div><!-- END OF DIV #WRAPPER -->


<script src="js/CCA_FF.js"></script>

</body>
</html>